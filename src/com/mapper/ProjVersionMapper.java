package com.mapper;

import java.util.List;
import java.util.Map;

import com.entity.ProjVersion;

/**
 * @comment 版本信息mapper接口
 * @author changjiaqi
 * @date 2018年7月24日 下午10:28:27
 * @version TODO
 */
public interface ProjVersionMapper {

	/**
	 * @comment: 查询总条数
	 * @return int
	 * @author changjiaqi
	 * @date 2018年7月24日
	 */
	public int findCountProjVersion(Map map);

	/**
	 * @comment: 分页展示列表信息
	 * @param map
	 * @return List<ProjVersion>
	 * @author changjiaqi
	 * @date 2018年7月24日
	 */
	public List<ProjVersion> findProjVersion(Map map);

	/**
	 * @comment: 添加版本信息
	 * @param map
	 * @return int
	 * @author changjiaqi
	 * @date 2018年7月25日
	 */
	public int addProjVersion(Map map);

	/**
	 * @comment: 修改版本信息
	 * @param projVersion
	 * @return int
	 * @author changjiaqi
	 * @date 2018年7月25日
	 */
	public int updateProjVersion(ProjVersion projVersion);

	/**
	 * @comment: 删除版本信息
	 * @param projVersion
	 * @return int
	 * @author changjiaqi
	 * @date 2018年7月25日
	 */
	public int deleteProjVersion(ProjVersion projVersion);

	/**
	 * @comment: 查询版本号
	 * @return List<ProjVersion>
	 * @author changjiaqi
	 * @date 2018年7月25日
	 */
	public String findProjNum();
}
