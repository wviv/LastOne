//package com.chang.viv.user.utils;
//
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.core.toolkit.Assert;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.InjectionConfig;
//import com.baomidou.mybatisplus.generator.config.*;
//import com.baomidou.mybatisplus.generator.config.po.TableInfo;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.google.common.collect.Lists;
//
//import java.nio.file.Paths;
//import java.util.List;
//import java.util.ResourceBundle;
//
//public class MybatisPlusUtil {
//
//	public static void main(String[] args) {
//		generator();
//	}
//
//	public static void generator() {
//		ResourceBundle resource = ResourceBundle.getBundle("generator");
//		String driverName = resource.getString("jdbc.driver.name");
//		String url = resource.getString("jdbc.url");
//		String username = resource.getString("jdbc.username");
//		String password = resource.getString("jdbc.password");
//		String author = resource.getString("project.author");
//		String parent = resource.getString("project.parent.package");
//		String table = resource.getString("project.table");
//		Assert.notEmpty(url, "url不能为空", new Object[0]);
//		Assert.notEmpty(table, "table不能为空", new Object[0]);
//		Assert.notEmpty(parent, "parent不能为空", new Object[0]);
//		Assert.notEmpty(author, "author不能为空", new Object[0]);
//		Assert.notEmpty(username, "username不能为空", new Object[0]);
//		Assert.notEmpty(password, "password不能为空", new Object[0]);
//		Assert.notEmpty(driverName, "driverName不能为空", new Object[0]);
//		AutoGenerator mpg = new AutoGenerator();
//		GlobalConfig gc = new GlobalConfig();
//		gc.setOutputDir(Paths.get("/Users/wenchang/Desktop/generator/src/java", new String[0]).toString());
//		gc.setServiceName("%sService");
//		gc.setFileOverride(true);
//		gc.setOpen(false);
//		gc.setActiveRecord(false);
//		gc.setSwagger2(true);
//		gc.setAuthor(author);
//		gc.setOpen(true);
//		gc.setIdType(IdType.UUID);
//		mpg.setGlobalConfig(gc);
//		DataSourceConfig dsc = new DataSourceConfig();
//		dsc.setUrl(url);
//		dsc.setDriverName(driverName);
//		dsc.setUsername(username);
//		dsc.setPassword(password);
//		mpg.setDataSource(dsc);
//		PackageConfig pc = new PackageConfig();
//		pc.setParent(parent);
//		mpg.setPackageInfo(pc);
//		InjectionConfig cfg = new InjectionConfig() {
//			@Override
//			public void initMap() {
//			}
//		};
//		String templatePath = "/templates/mapper.xml.ftl";
//		List<FileOutConfig> focList = Lists.newArrayList();
//		focList.add(new FileOutConfig(templatePath) {
//			@Override
//			public String outputFile(TableInfo tableInfo) {
//				return Paths.get("/Users/wenchang/Desktop/generator/src/resources/mappers", new String[0]).toString() + "/" + tableInfo.getEntityName() + "Mapper" + ".xml";
//			}
//		});
//		cfg.setFileOutConfigList(focList);
//		mpg.setCfg(cfg);
//		TemplateConfig templateConfig = new TemplateConfig();
//		templateConfig.setXml((String) null);
//		mpg.setTemplate(templateConfig);
//		StrategyConfig strategy = new StrategyConfig();
//		strategy.setNaming(NamingStrategy.underline_to_camel);
//		strategy.setLogicDeleteFieldName("is_deleted");
//		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//		strategy.setEntityLombokModel(true);
//		strategy.setEntityBuilderModel(true);
//		strategy.setRestControllerStyle(true);
//		strategy.setInclude(table.split(","));
//		strategy.setControllerMappingHyphenStyle(true);
//		strategy.setTablePrefix(abcds());
//		mpg.setStrategy(strategy);
//		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
//		mpg.execute();
//	}
//
//	/**
//	 * 26个字母
//	 */
//	public static String[] abcds() {
//		String[] abcds = new String[26];
//		for(int i = 0;i<26;i++){
//			abcds[i] = (char) (97 + i) + "_";
//		}
//		return abcds;
//	}
//}
