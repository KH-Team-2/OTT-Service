		package com.biz.alarm;


		import java.util.List;

import com.dto.UserDto;

		public interface AlarmBiz {
			public boolean AlarmSwitch(int num);
			 public List<UserDto> AlarmLoading();
		}
