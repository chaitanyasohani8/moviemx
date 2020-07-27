package com.lti.movie.validation;

public class DataValidator 
{
	public static boolean isNull(String val) 
	{
		if (val == null || val.trim().length() == 0)
		{
			return true;
		}
		else 
		{
			return false;

		}
	}

}
