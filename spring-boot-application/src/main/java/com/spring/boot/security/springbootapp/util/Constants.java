package com.spring.boot.security.springbootapp.util;

public interface Constants {

	public static enum ITEM_CATEGORY{
		EDUCATIONAL{
			public String toString(){
				return "educational";
			}
		},
		HOUSEHOLD{
			public String toString(){
				return "house-hold";
			}
		},
		ELECTRONICS{
			public String toString(){
				return "electronics";
			}
		},
		SAMPLE{
			public String toString(){
				return "sample_category";
			}
		}
	}
	
	public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING_AUTH = "Authorization";
    public static final String SIGN_UP_URL = "/user/register";
    public static final String ITEM_URL = "/item*";
}
