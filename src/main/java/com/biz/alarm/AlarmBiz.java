		package com.biz.alarm;


		import java.util.List;

		import com.dto.AlarmDto;

		public interface AlarmBiz {
			public void AlarmSwitch(int num);
			public List<AlarmDto> AlarmLoading();
		}
