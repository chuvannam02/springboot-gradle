package com.learning.springboot_gradle.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @Project: springboot-gradle
 * @Package: com.learning.springboot_gradle.entities  *
 * @Author: ChuVanNam
 * @Date: 11/26/2025
 * @Time: 5:30 PM
 */

@MappedSuperclass // Đánh dấu đây là class cha, các cột của nó sẽ được map vào bảng con
@EntityListeners(AuditingEntityListener.class) // Lắng nghe sự kiện để tự động điền ngày giờ
@Getter
@Setter
public abstract class BaseEntity {

	@CreatedDate // Tự động điền ngày tạo
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate // Tự động điền ngày sửa mỗi khi update
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
}
