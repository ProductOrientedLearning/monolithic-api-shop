package pol.ecom.api.shop.entity;
/*
 * This is course Microservice Product Oriented
 * MIT No Attribution

 * Copyright (c) <2023> <Dr.JohnLe & Mr.HaNguyen>

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 */

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.util.ObjectUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "product")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID")
    private UUID id;

    @Column(name = "name")
    private String name;
    @Column(name = "unit")
    private String unit;
    @Column(name = "price")
    private Double price;
    @Column(name = "special_price")
    private Double specialPrice;
    @Column(name = "from_date")
    private Date fromDate;
    @Column(name = "to_date")
    private Date toDate;
    @Column(name = "image", columnDefinition = "TEXT")
    @Lob
    private String image;
    @Column(name = "sku", unique = true)
    private String sku;
    @Column(name = "type")
    private String productType;
    @Column(name = "active")
    private boolean active;
    @Column(name = "in_stock")
    private boolean inStock;
    @Column(name = "map_category")
    private boolean mapCategory;

    @Column(name = "info_product", columnDefinition = "TEXT")
    @Lob
    private String info;

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Brand brand;

    @Transient
    private Double finalPrice;

    public Double getFinalPrice(){
        Date currentDate = new Date();
        if (!ObjectUtils.isEmpty(fromDate) && !ObjectUtils.isEmpty(toDate)
                && fromDate.before(currentDate) && toDate.after(currentDate)
                && !ObjectUtils.isEmpty(specialPrice)) {
            finalPrice = specialPrice;
        } else {
            finalPrice = price;
        }
        return finalPrice;
    }

}
