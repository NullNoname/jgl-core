/*
 * @(#)JGLSysErrLogger.java 0.1 13/09/13
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

import java.io.Serializable;

/**
 * A logger that output messages to System.err.
 * @author NullNoname
 */
public class JGLSysErrLogger extends JGLDummyLogger implements Serializable {
	private static final long serialVersionUID = 418966197101533520L;

	public void logError(String msg) {
		System.err.println((msg == null) ? "null" : msg);
	}

	public void logError(String msg, Throwable t) {
		System.err.println((msg == null) ? "null" : msg);
		if(t != null) t.printStackTrace();
	}
}
