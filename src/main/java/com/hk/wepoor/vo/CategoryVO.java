package com.hk.wepoor.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVO {
	int cate_id;
	String cate_name;
	int cate_cost;
	String cate_date;
	int cate_weekend;
}
