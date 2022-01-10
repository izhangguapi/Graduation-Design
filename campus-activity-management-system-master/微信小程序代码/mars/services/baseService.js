import baseRepository from 'repository/baseRepository';

/**
 * @explain：业务类
 */
class baseSerivce {
	
	/**
     * @explain：构造函数，可进行数据校验
     */
    constructor(params) {
        
    }

    /**
     * 保存对象数据
     */
    save() {
        return baseRepository.save({
			//参数
		})
    }
	
}

module.exports = baseSerivce;