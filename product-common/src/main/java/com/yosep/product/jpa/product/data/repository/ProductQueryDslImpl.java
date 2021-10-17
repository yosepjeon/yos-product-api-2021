package com.yosep.product.jpa.product.data.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yosep.product.jpa.category.data.entity.QCategory;
import com.yosep.product.jpa.common.util.QueryDslUtil;
import com.yosep.product.jpa.product.data.dto.*;
import com.yosep.product.jpa.product.data.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static com.yosep.product.jpa.product.data.entity.QProductProfileImage.productProfileImage;

@Repository
@RequiredArgsConstructor
public class ProductQueryDslImpl implements ProductQueryDsl {
    private final JPAQueryFactory jpaQueryFactory;
    QProduct product = QProduct.product;
    QCategory category = QCategory.category;
    QProductImage productImage = QProductImage.productImage;
    QProductComment productComment = QProductComment.productComment;
    QProductCommentImage productCommentImage = QProductCommentImage.productCommentImage;
    QProductDescriptionImage productDescriptionImage = QProductDescriptionImage.productDescriptionImage;

    @Override
    public Optional<SelectedProductDtoForDetailPage> findByIdForDetailPage(String productId) {
        Optional<Product> optionalProduct = Optional.of(jpaQueryFactory
                .selectFrom(product)
                .where(product.productId.eq(productId))
                .fetchOne());

        if (optionalProduct.isEmpty()) {
            return Optional.empty();
        }

        List<SelectedProductImageDto> productImages = findSelectedProductImageDtos(productId);
        List<SelectedProductCommentDto> comments = findSelectedProductCommentDtos(productId);
        List<SelectedProductDescriptionImageDto> descriptions = findSelectedProductDescriptionImageDtos(productId);

        Product seletedProduct = optionalProduct.get();
        SelectedProductDtoForDetailPage result = new SelectedProductDtoForDetailPage(
                seletedProduct.getProductId(),
                seletedProduct.getProductName(),
                seletedProduct.getCompanyCode(),
                seletedProduct.getProductPrice(),
                seletedProduct.getStockQuantity(),
                seletedProduct.getProductDetail(),
                productImages,
                comments,
                descriptions
        );

        return Optional.of(result);
    }

    private List<SelectedProductImageDto> findSelectedProductImageDtos(String productId) {
        return jpaQueryFactory
                .select(
                        new QSelectedProductImageDto(
                                productImage.url
                        )
                )
                .distinct()
                .from(productImage)
                .leftJoin(productImage.product, product)
                .where(productImage.product.productId.eq(productId))
                .fetch();
    }

    private List<SelectedProductCommentDto> findSelectedProductCommentDtos(String productId) {
        List<SelectedProductCommentDto> result = jpaQueryFactory
                .selectFrom(productComment)
                .distinct()
                .leftJoin(productComment.product, product).fetchJoin()
                .leftJoin(productComment.commentImages, productCommentImage).fetchJoin()
                .where(productComment.product.productId.eq(productId))
                .fetch()
                .stream()
                .map(c -> {
                    List<SelectedProductCommentImageDto> images = c.getCommentImages().stream()
                            .map(image -> new SelectedProductCommentImageDto(image.getUrl()))
                            .collect(Collectors.toList());

                    return new SelectedProductCommentDto(c.getId(), c.getComment(), images, c.getCreatedDate());
                })
                .collect(Collectors.toList());

        return result.isEmpty() ? Collections.emptyList() : result;
    }

    private List<SelectedProductDescriptionImageDto> findSelectedProductDescriptionImageDtos(String productId) {
        List<SelectedProductDescriptionImageDto> result = jpaQueryFactory
                .select(
                        new QSelectedProductDescriptionImageDto(
                                productDescriptionImage.url
                        )
                )
                .distinct()
                .from(productDescriptionImage)
                .leftJoin(productDescriptionImage.product, product).fetchJoin()
                .where(productDescriptionImage.product.productId.eq(productId))
                .fetch();

        return result.isEmpty() ? Collections.emptyList() : result;
    }

    @Override
    public Optional<List<SelectedProductDtoV2>> findAllByCategory(PageRequest pageRequest, String categoryId) {
        List<OrderSpecifier> ORDERS = getAllOrderSpecifiers(pageRequest);

        Optional<List<SelectedProductDtoV2>> products = Optional.of(jpaQueryFactory
                .select(
                        new QSelectedProductDtoV2(
                                product.productId,
                                product.productName,
                                product.productPrice,
                                product.stockQuantity,
                                product.productDiscount,
                                product.thumbnail.id,
                                product.thumbnail.url
                        )
                )
                .distinct()
                .from(product)
                .leftJoin(product.category, category)
                .leftJoin(product.thumbnail, productProfileImage)
                .where(product.category.categoryId.eq(categoryId))
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .orderBy(ORDERS.stream().toArray(OrderSpecifier[]::new))
                .fetch()
        );

//        List<String> productIds = toProductIds(products.orElse(Collections.emptyList()));
//        Map<String, List<ProductImageDto>> productImages = findProductImages(productIds);
//        products.orElse(Collections.emptyList()).forEach(p -> p.setThumbnail(null));

        return products;
    }

    @Override
    public Optional<List<Product>> findAllByCategory(String categoryId) {

        Optional<List<Product>> products = Optional.of(jpaQueryFactory
                .selectFrom(product)
                .leftJoin(product.category, category).fetchJoin()
                .where(product.category.categoryId.eq(categoryId))
                .orderBy(product.createdDate.asc())
                .fetch()
        );

        return products;
    }

    private List<String> toProductIds(List<SelectedProductDtoV2> products) {
        return products.stream()
                .map(SelectedProductDtoV2::getProductId)
                .collect(Collectors.toList());
    }

    private Map<String, List<ProductImageDto>> findProductImages(List<String> productIds) {

        return jpaQueryFactory
                .select(
                        Projections.constructor(
                                ProductImageDto.class,
                                productImage.product.productId,
                                productImage.id,
                                productImage.url
                        )
                )
                .from(productImage)
                .distinct()
                .where(productImage.product.productId.in(productIds))
                .fetch()
                .stream()
                .collect(Collectors.groupingBy(ProductImageDto::getProductId));
    }

    private List<OrderSpecifier> getAllOrderSpecifiers(PageRequest pageRequest) {
        List<OrderSpecifier> ORDERS = new ArrayList<>();

        if (!pageRequest.getSort().isEmpty()) {
            for (Sort.Order order : pageRequest.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;

                switch (order.getProperty()) {
                    case "name":
                        OrderSpecifier<?> orderId = QueryDslUtil.getSortedColumn(direction, product, "productName");
                        ORDERS.add(orderId);
                        break;
                    case "createdDate":
                        OrderSpecifier<?> createdDate = QueryDslUtil.getSortedColumn(direction, product, "createdDate");
                        ORDERS.add(createdDate);
                        break;
                    default:
                        break;
                }
            }
        }

        return ORDERS;
    }
}
