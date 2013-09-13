/*
 * @(#)JGLLogger.java 0.1 13/09/13
 *
 * jGL 3-D graphics library for Java
 * Copyright (c) 1996-2003 Robin Bing-Yu Chen (robin@nis-lab.is.s.u-tokyo.ac.jp)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or any later version. the GNU Lesser
 * General Public License should be included with this distribution
 * in the file LICENSE.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 */
package com.github.nullnoname.jgl.core.logging;

/**
 * Logger interface that handles jGL-related errors.
 * @author NullNoname
 */
public interface JGLLogger {
	/**
	 * Log an error message
	 * @param msg Error Message
	 */
	public void logError(String msg);

	/**
	 * Log an error message with an accompanying throwable
	 * @param msg Error Message
	 * @param t Throwable
	 */
	public void logError(String msg, Throwable t);
}
