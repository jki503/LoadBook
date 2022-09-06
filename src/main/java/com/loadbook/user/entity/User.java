package com.loadbook.user.entity;

import static javax.persistence.GenerationType.*;
import static javax.persistence.InheritanceType.*;
import static lombok.AccessLevel.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

import org.springframework.util.Assert;

import com.loadbook.common.entity.BaseEntity;
import com.loadbook.user.entity.vo.BaseUserInformation;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = PROTECTED)
@Inheritance(strategy = JOINED)
@DiscriminatorColumn
public abstract class User extends BaseEntity {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Embedded
	private BaseUserInformation userInformation;

	public User(BaseUserInformation userInformation) {
		Assert.notNull(userInformation, "유저 정보를 입력하세요.");
		this.userInformation = userInformation;
	}
}
