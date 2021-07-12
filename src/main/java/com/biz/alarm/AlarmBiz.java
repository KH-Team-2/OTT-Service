		package com.biz.alarm;


		import java.util.List;

import com.dto.UserDto;

		public interface AlarmBiz {
			public void AlarmSwitch(int num);
			public List<UserDto> AlarmLoading(int alarmnum);
		}
