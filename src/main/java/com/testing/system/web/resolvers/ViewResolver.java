package com.testing.system.web.resolvers;

import com.testing.system.exceptions.builders.ViewResolverBuildingException;

public class ViewResolver {

	private String prefix;
	private String suffix;

	private ViewResolver(String prefix, String suffix){
		this.prefix = prefix;
		this.suffix = suffix;
	}

	public String resolve(String viewName){
		if(viewName == null){
			return null;
		}
		return prefix + viewName + suffix;
	}

	public static ViewResolverBuilder getBuilder(){
		return new ViewResolverBuilder();
	}

	static public class ViewResolverBuilder{

		String prefix;
		String suffix;

		private ViewResolverBuilder() {
		}

		public ViewResolverBuilder setPrefix(String prefix){
			this.prefix = prefix;
			return this;
		}

		public ViewResolverBuilder setSuffix(String suffix){
			this.suffix = suffix;
			return this;
		}

		public ViewResolver create(){
			if(prefix == null || suffix == null){
				throw new ViewResolverBuildingException("Suffix and prefix shouldn't be null");
			}
			return new ViewResolver(prefix, suffix);
		}

	}

}

