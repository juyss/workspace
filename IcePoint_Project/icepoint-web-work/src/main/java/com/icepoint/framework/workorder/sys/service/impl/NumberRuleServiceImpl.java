package com.icepoint.framework.workorder.sys.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import javax.annotation.Resource;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;

import com.icepoint.framework.core.util.TimestampUtils;
import com.icepoint.framework.web.security.entity.User;
import com.icepoint.framework.workorder.sys.dao.NumberRuleItemMapper;
import com.icepoint.framework.workorder.sys.dao.NumberRuleItemRepository;
import com.icepoint.framework.workorder.sys.dao.NumberRuleMapper;
import com.icepoint.framework.workorder.sys.entity.NumberRule;
import com.icepoint.framework.workorder.sys.entity.NumberRuleItem;
import com.icepoint.framework.workorder.sys.service.NumberRuleService;
import org.springframework.stereotype.Service;

@Service
public class NumberRuleServiceImpl implements NumberRuleService {
	@Resource
    private NumberRuleMapper numberRuleMapper;
	@Resource
    private NumberRuleItemRepository numberRuleItemRepository;
	@Resource
    private NumberRuleItemMapper numberRuleItemMapper;
	@Resource
	private RedisTemplate  redisTemplate;
	
	@Override
	public NumberRule add(NumberRule entity) {
		//获取登录用户，设置参数
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (null == user){
			return null;
		}
		if (null == entity.getAppId()){
			entity.setAppId(user.getAppId());
		}
		if (null == entity.getPlatformId()){
			entity.setPlatformId(user.getPlatformId());
		}
		if (null == entity.getOwnerId()){
			entity.setOwnerId(user.getOwnerId());
		}
		if (null == entity.getCreateUserId()){
			entity.setCreateUserId(user.getId());
		}
		if (null == entity.getCreateTime()){
		//	entity.setCreateTime(TimestampUtils.getDatetime());
		}
		if (null == entity.getUpdateUserId()){
			entity.setUpdateUserId(user.getId());
		}
		if (null == entity.getUpdateTime()){
	//		entity.setUpdateTime(TimestampUtils.getDatetime());
		}
		
		//为NumberRuleItem设置序号
		IntStream intStream = entity.getItems().parallelStream().mapToInt(item -> item.getIdx());
		OptionalInt imax = intStream.max();
		int maxVal = 0;
		if (imax.isPresent()) {
			maxVal = imax.getAsInt();
        }
		for (int i = 0; i < entity.getItems().size(); ++i){
			NumberRuleItem item = entity.getItems().get(i);
			if (null == item.getIdx() || item.getIdx() <= 0){
				item.setIdx(++maxVal);
			}
		}
		
		//添加NumberRule
		entity = numberRuleMapper.save(entity);
		
		//为NumberRuleItem设置ruleId及其他参数

		//添加NumberRuleItem
		
		return entity;
	}

	@Override
	public NumberRule edit(NumberRule entity) {
		//获取登录用户，设置参数
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (null == user) {
			return null;
		}
		if (null == entity.getId() || entity.getId() <= 0) {
			return null;
		}

		if (null == entity.getUpdateUserId()) {
			entity.setUpdateUserId(user.getId());
		}
		if (null == entity.getUpdateTime()) {
		//	entity.setUpdateTime(TimestampUtils.getDatetime());
		}

		//根据ruleId删除原有NumberRuleItem
		NumberRuleItem oldQuery = new NumberRuleItem();
		oldQuery.setAppId(user.getAppId());
		oldQuery.setPlatformId(user.getPlatformId());
		oldQuery.setRuleId(entity.getId());
		List<NumberRuleItem> oldItems = numberRuleItemRepository.findAll(Example.of(oldQuery), false);
		numberRuleItemRepository.deleteAll(oldItems);

		//为NumberRuleItem设置序号
		IntStream intStream = entity.getItems().parallelStream().mapToInt(item -> item.getIdx());
		OptionalInt imax = intStream.max();
		int maxVal = 0;
		if (imax.isPresent()) {
			maxVal = imax.getAsInt();
        }
		for (int i = 0; i < entity.getItems().size(); ++i){
			NumberRuleItem item = entity.getItems().get(i);
			if (null == item.getIdx() || item.getIdx() <= 0){
				item.setIdx(++maxVal);
			}
		}
		
		//修改NumberRule
		entity = numberRuleMapper.save(entity);
		
		//为NumberRuleItem设置ruleId及其他参数
		
	

		//添加新NumberRuleItem
		return entity;
	}
	
	@Override
	public Boolean editLatestNum(Long assertId,String latestNum) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (null == user) {
			return false;
		}
		
		NumberRule oldQuery = new NumberRule();
		oldQuery.setAppId(user.getAppId());
		oldQuery.setPlatformId(user.getPlatformId());
		oldQuery.setAssertId(assertId);
		Optional<NumberRule> opt = numberRuleMapper.findOne(Example.of(oldQuery), false);
		if (!opt.isPresent()){
			return false;
		}
		
		NumberRule entity = opt.get();
		entity.setLatestNum(latestNum);
		entity = numberRuleMapper.save(entity);
		if (null != entity){
			return true;
		}
		return false;
	}

	@Override
	public Integer delete(List<Long> ids) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (null == user) {
			return null;
		}
		
		//根据ruleId查询NumberRuleItem
		List<NumberRuleItem> oldItems = numberRuleItemMapper.findAllByRuleIds(user.getAppId(), user.getPlatformId(), ids);
		//批量删除NumberRuleItem
		numberRuleItemRepository.deleteAll(oldItems);
		
		//批量删除NumberRule
		numberRuleMapper.deleteAllInId(ids);
		
		return ids.size();
	}

	@Override
	public Page<NumberRule> list(Map<String, Object> entity, Pageable pageable) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (null == user) {
			return null;
		}
		NumberRule numberRule = new NumberRule(entity);
		if (null == numberRule.getAppId()){
			numberRule.setAppId(user.getAppId());
		}
		if (null == numberRule.getPlatformId()){
			numberRule.setPlatformId(user.getPlatformId());
		}
		
		return numberRuleMapper.findAll(Example.of(numberRule), pageable, false);
	}

	public static String createNumber(){
		//TODO 需要根据编码规则进行生成
		return 	String.valueOf((Math.random()*10));
	}

	@Override
	public String getNumber(Long assertId) {
		//如果Redis里不存在，则生成初始值，写入redis
		if(redisTemplate.hasKey(String.valueOf(assertId))){
			//根据asserId生成一个编码
			redisTemplate.opsForValue().set(String.valueOf(assertId),createNumber());
			return createNumber();
		}
		//如果Redis存在，则取出直接返回
		return (String) redisTemplate.opsForValue().get(assertId);
	}

	@Override
	public String confirmNumber(Long assertId, String number) {
		//锁定Redis值
		String redisNumber =getNumber(assertId);
		if(redisNumber.equals(number)){
			redisTemplate.opsForValue().set(String.valueOf(assertId),number);
			return "ok";
		}else {
			//如果不相等  则把key移除 重新创建
			redisTemplate.opsForHash().delete(String.valueOf(assertId));
			return getNumber(assertId);
		}
	}

}
