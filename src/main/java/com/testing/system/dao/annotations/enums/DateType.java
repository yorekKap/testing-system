package com.testing.system.dao.annotations.enums;

import java.util.Date;


/**
 *Describes different ways {@code Date} could be parsed
 *
 * @author yuri
 */
public enum DateType {
	DATE{
		@Override
		public Object getDateInRightFormat(Date date) {
			return new java.sql.Date(date.getTime());
		}
	},

	TIME{
		@Override
		public Object getDateInRightFormat(Date date) {
			return new java.sql.Time(date.getTime());
		}

	},

	DATE_TIME{
		@Override
		public Object getDateInRightFormat(Date date) {
			return new java.sql.Timestamp(date.getTime());
		}
	};

	public abstract Object getDateInRightFormat(Date date);
}
