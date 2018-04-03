package com.oldneighborhood.demo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="pay")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class PayInfo {
	@Id
	private String pay_ID;
	@NonNull
	private String site_ID;
	@NonNull
	private String u_ID;
	@NonNull
	private double pay_price;
	@NonNull
	private int pay_amount;
	@NonNull
	private String pay_code;
	@Column(columnDefinition="timestamp not null default now()" , updatable=false)
	private Timestamp pay_date;
}
