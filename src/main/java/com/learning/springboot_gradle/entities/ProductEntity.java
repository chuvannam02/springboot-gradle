package com.learning.springboot_gradle.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

/**
 * @Project: springboot-gradle
 * @Package: com.learning.springboot_gradle.entities  *
 * @Author: ChuVanNam
 * @Date: 11/26/2025
 * @Time: 3:52 PM
 */

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// 1. Khi gọi lệnh delete(), Hibernate sẽ chạy câu SQL này thay vì DELETE thật
@SQLDelete(sql = "UPDATE products SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
// 2. Khi gọi lệnh select (findAll, findById), tự động thêm điều kiện này
@Where(clause = "deleted_at IS NULL")
public class ProductEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;
}
