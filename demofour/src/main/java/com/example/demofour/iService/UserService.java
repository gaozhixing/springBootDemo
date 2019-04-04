package com.example.demofour.iService;

import com.example.demofour.dao.TUserMapper;
import com.example.demofour.entity.*;
import com.example.demofour.util.ConstantUtil;
import com.example.demofour.util.ResultUtil;
import com.example.demofour.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserService {

	@Autowired
	private TUserMapper tUserMapper;

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
			
	public BaseResult<String> createUser(TUser user) {
		BaseResult<String> result = ResultUtil.success();
		if (user != null) {
			if (!StringUtil.isBlank(user.getId())) {
				tUserMapper.insert(user);
				result.setData(user.getId());
				return result;
			}
			return ResultUtil.error(ConstantUtil.ResultCode.COMMON_ERROR_CODE, "请求参数Id为空");
		}
		return ResultUtil.error(ExceptionEnum.REQUEST_IS_NULL);
	}
	

	

	
	public TUser queryUserById(String id) {
		return tUserMapper.queryUserById(id);
	}

	
	/*public PageResult<User> queryUserListByPage(Page page) {
		PageResult<User> result = (PageResult<User>)ResultUtil.success();
		PageHelper.startPage(page);
		List<User> userList = userDao.queryUserList();
		result.setResult(userList);
		result.setCount(((Page)userList).getTotalElements());
		result.setPageNum(page.getNumber());
		result.setPageSize(page.getSize());
		return result;
	}*/
}
