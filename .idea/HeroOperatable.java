import java.util.List;

public interface HeroOperatable {
    /**
     * 查询所有数据
     * @return  将所有数据封装到集合中返回
     */
    List<Hero>  findAllHero();

    /**
     * 修改信息
     * @param hero 待修改的对象
     * @return 执行sql后，受影响的行数
     */
    int updateHeroById(Hero hero);

    /**
     * 添加数据
     * @param hero
     * @return 执行sql后，受影响的行数
     */
    int insertHero(Hero hero);

    /**
     * 根据id删除
     * @param id
     * @return 执行sql后，受影响的行数
     */
    int deleteHeroById(int id);

}
