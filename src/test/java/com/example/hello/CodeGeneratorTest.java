package com.example.hello;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @Desc: 代码生成器
 * @author:Angelaboy
 */
@RunWith(SpringRunner.class)
//@SpringBootTest
public class CodeGeneratorTest {

    //配置表名
    private static final String TABLE_NAME = "user";

    //配置作者名字
    private static final String AUTHOR = "Angelaboy";

    @Test
    public void execute() {
        // 代码生成器,默认模板引擎是 velocity
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("Angelaboy");
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);


        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/hello?useUnicode=true&characterEncoding=gbk&useSSL=false&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("user");
        pc.setParent("com.example");
        pc.setEntity("pojo.entity");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map map = new HashMap();
                map.put("author",AUTHOR);
                this.setMap(map);
            }
        };

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(final TableInfo tableInfo) {
                // 自定义输出文件名
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + ".xml";
            }
        });

        focList.add(new FileOutConfig("/templates/entity.java.vm") {
            @Override
            public String outputFile(final TableInfo tableInfo) {
                // 自定义输出文件名
                return projectPath + "/src/main/java/com/example/user/pojo/entity/" + tableInfo.getEntityName() + "Entity" + ".java";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        templateConfig.setEntity(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);//类命名去掉下划线
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//属性码命名去掉下划线
        strategy.setTablePrefix(pc.getModuleName()+"_");//设置表前缀
        strategy.setInclude(TABLE_NAME);//设置表名
        mpg.setStrategy(strategy);
        mpg.execute();
    }
}
