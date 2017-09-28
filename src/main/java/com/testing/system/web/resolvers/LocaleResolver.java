package com.testing.system.web.resolvers;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.testing.system.exceptions.builders.LocaleResolverBuildingException;


/**
 * Serve for getting the right locale.
 *
 * @author yuri
 *
 */
public class LocaleResolver {

	Map<String, Locale> locales;
	Locale defaultLocale;

	private LocaleResolver() {
		 locales = new HashMap<>();
		 defaultLocale = null;
	}

	private LocaleResolver(Map<String, Locale> locales, Locale defaultLocale) {
		this.locales = locales;
		this.defaultLocale = defaultLocale;
	}


	/**
	 * @return default {@link Locale}
	 */
	public Locale getDefaultLocale(){
		return defaultLocale;
	}


	/**
	 * Get locale based on the given {@code lang} parameter
	 *
	 * @param lang - language code
	 * @return {@link Locale} for a given {@code lang} if there is
	 * 			no such {@link Locale} - return default {@link Locale}
	 */
	public Locale getLocale(String lang) {
		Locale locale = null;
		locale = locales.get(lang);

		if(locale == null){
			return defaultLocale;
		}

		return locale;
	}

	/**
	 * @return {@link LocaleResolverBuilder}
	 */
	public static LocaleResolverBuilder getBuilder() {
		return new LocaleResolverBuilder();
	}


	/**
	 * Class for convenient {@link LocaleResolver} building
	 *
	 * @author yuri
	 *
	 */
	public static class LocaleResolverBuilder {

		private Map<String, Locale> locales = new HashMap<>();
		private Locale defaultLocale;

		private LocaleResolverBuilder() {
		}

		public LocaleResolverBuilder setDefaultLocale(Locale locale) {
			this.defaultLocale = locale;
			return this;
		}

		public LocaleResolverBuilder addLocale(String lang, Locale locale) {
			locales.put(lang, locale);
			return this;
		}

		public LocaleResolver createLocaleResolver(){
			if(defaultLocale == null){
				throw new LocaleResolverBuildingException("The default locale must be set in LocaleResolver");
			}

			return new LocaleResolver(locales, defaultLocale);
		}

	}
}
