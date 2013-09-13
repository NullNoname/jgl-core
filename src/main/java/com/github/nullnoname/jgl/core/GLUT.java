/*
 * @(#)GLUT.java 0.3 03/05/10
 *
 * jGL 3-D graphics library for Java
 * Copyright (c) 2001-2003 Robin Bing-Yu Chen (robin@nis-lab.is.s.u-tokyo.ac.jp)
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

package com.github.nullnoname.jgl.core;

import java.io.Serializable;

import com.github.nullnoname.jgl.core.glaux.teapot;
import com.github.nullnoname.jgl.core.glu.GLUquadricObj;

/**
 * GLUT is the glut class of jGL 2.4.
 *
 * @version 	0.3, 10 May 2003
 * @author 	Robin Bing-Yu Chen
 */
public class GLUT implements Serializable {
	private static final long serialVersionUID = 6238214816724252116L;

    /**
     * glut Functions
     *     Java is not C language, not all the glut functions
     *     can be implemented, only implement some useful functions.
     */

	/** Constants of glut */
    /* Mouse buttons. */
    public static final int GLUT_LEFT_BUTTON			= 0;
    public static final int GLUT_MIDDLE_BUTTON			= 1;
    public static final int GLUT_RIGHT_BUTTON			= 2;

    /* Mouse button  state. */
    public static final int GLUT_DOWN				= 0;
    public static final int GLUT_UP				= 1;

    /* glutGetModifiers return mask. */
    public static final int GLUT_ACTIVE_SHIFT			= 1;
    public static final int GLUT_ACTIVE_CTRL			= 2;
    public static final int GLUT_ACTIVE_ALT			= 4;

    /* function keys */
    public static final int GLUT_KEY_F1				= 1;
    public static final int GLUT_KEY_F2				= 2;
    public static final int GLUT_KEY_F3				= 3;
    public static final int GLUT_KEY_F4				= 4;
    public static final int GLUT_KEY_F5				= 5;
    public static final int GLUT_KEY_F6				= 6;
    public static final int GLUT_KEY_F7				= 7;
    public static final int GLUT_KEY_F8				= 8;
    public static final int GLUT_KEY_F9				= 9;
    public static final int GLUT_KEY_F10			= 10;
    public static final int GLUT_KEY_F11			= 11;
    public static final int GLUT_KEY_F12			= 12;

    /* directional keys */
    public static final int GLUT_KEY_LEFT			= 100;
    public static final int GLUT_KEY_UP				= 101;
    public static final int GLUT_KEY_RIGHT			= 102;
    public static final int GLUT_KEY_DOWN			= 103;
    public static final int GLUT_KEY_PAGE_UP			= 104;
    public static final int GLUT_KEY_PAGE_DOWN			= 105;
    public static final int GLUT_KEY_HOME			= 106;
    public static final int GLUT_KEY_END			= 107;
    public static final int GLUT_KEY_INSERT			= 108;

    /** Private Data Members */
    private GL JavaGL;
    private GLU JavaGLU;
    private GLUquadricObj quadObj;

    /** Private Member Functions */
/*
    private void error (String s) {
	// Cancel the "errfunc" in aux of OpenGL
	System.out.println (s);
	System.exit (1);
    }

    private void DIFF3 (double p[], double q[], double diff[]) {
	diff[0] = p[0] - q[0];
	diff[1] = p[1] - q[1];
	diff[2] = p[2] - q[2];
    }

    private void crossprod (double v1[], double v2[], double prod[]) {
	double p[] = new double [3];	*//* in case prod == v1 or v2 */
/*
	p[0] = v1[1]*v2[2] - v2[1]*v1[2];
	p[1] = v1[2]*v2[0] - v2[2]*v1[0];
	p[2] = v1[0]*v2[1] - v2[0]*v1[1];
	prod[0] = p[0]; prod[1] = p[1]; prod[2] = p[2];
    }

    private void normalize (double v[]) {
	double d;

	d = Math.sqrt (v [0] * v [0] + v [1] * v [1] + v [2] * v [2]);
	if (d == 0.0) {
	    error ("normalize: zero length vector");
	    v [0] = d = 1.0;
	}
	d = 1/d;
	v[0] *= d; v[1] *= d; v[2] *= d;
    }

    private void m_xformpt (double pin [], double pout [],
			    double nin [], double nout []) {
	int i;
	double ptemp [] = new double [3];
	double ntemp [] = new double [3];
	mat_t m = matstack [mattop];

	if (identitymat) {
	    for (i = 0; i < 3; i++) {
		pout [i] = pin [i];
		nout [i] = nin [i];
	    }
	    return;
	}
	for (i = 0; i < 3; i++) {
	    ptemp [i] = pin [0] * m.mat [0][i] +
	    		pin [1] * m.mat [1][i] +
			pin [2] * m.mat [2][i] +
			m.mat[3][i];
	    ntemp [i] = nin [0] * m.norm [0][i] +
                   	nin [1] * m.norm [1][i] +
                   	nin [2] * m.norm [2][i];
	}
	for (i = 0; i < 3; i++) {
	    pout [i] = ptemp [i];
	    nout [i] = ntemp [i];
	}
	normalize(nout);
    }

    private void m_xformptonly (double pin[], double pout[]) {
	int i;
	double	ptemp[] = new double [3];
	mat_t m = matstack [mattop];

	if (identitymat) {
	    for (i = 0; i < 3; i++) {
		pout[i] = pin[i];
	    }
	    return;
	}
 	for (i = 0; i < 3; i++) {
	    ptemp[i] = pin[0]*m.mat[0][i] + pin[1]*m.mat[1][i] +
		       pin[2]*m.mat[2][i] + m.mat[3][i];
	}
	for (i = 0; i < 3; i++) {
	    pout[i] = ptemp[i];
	}
    }

    private void recorditem (double n1 [], double n2 [], double n3 [],
		double center [], double radius, int shadeType, int avnormal) {
	double p1 [] = new double [3];
	double p2 [] = new double [3];
	double p3 [] = new double [3];
	double q0 [] = new double [3];
	double q1 [] = new double [3];
	double n11 [] = new double [3];
	double n22 [] = new double [3];
	double n33 [] = new double [3];
	int i;

	for (i = 0; i < 3; i++) {
	     p1[i] = n1[i]*radius + center[i];
	     p2[i] = n2[i]*radius + center[i];
	     p3[i] = n3[i]*radius + center[i];
	}
	if (avnormal == 0) {
	    diff3(p1, p2, q0);
	    diff3(p2, p3, q1);
	    crossprod(q0, q1, q1);
	    normalize(q1);
	    m_xformpt(p1, p1, q1, n11);
	    m_xformptonly(p2, p2);
	    m_xformptonly(p3, p3);

	    JavaGL.glBegin (shadeType);
	    JavaGL.glNormal3dv(n11);
	    JavaGL.glVertex3dv(p1);
	    JavaGL.glVertex3dv(p2);
	    JavaGL.glVertex3dv(p3);
	    JavaGL.glEnd();
	    return;
	}
	m_xformpt(p1, p1, n1, n11);
	m_xformpt(p2, p2, n2, n22);
	m_xformpt(p3, p3, n3, n33);

	JavaGL.glBegin (shadeType);
	JavaGL.glNormal3dv(n11);
	JavaGL.glVertex3dv(p1);
	JavaGL.glNormal3dv(n22);
	JavaGL.glVertex3dv(p2);
	JavaGL.glNormal3dv(n33);
	JavaGL.glVertex3dv(p3);
	JavaGL.glEnd();
    }

    private void subdivide (int depth, double v0 [], double v1 [], double v2 [],
		double p0 [], double radius, int shadeType, int avnormal) {
	double w0 [] = new double [3];
	double w1 [] = new double [3];
	double w2 [] = new double [3];
	double l;
	int i, j, k, n;

	for (i = 0; i < depth; i++) {
	    for (j = 0; i + j < depth; j++) {
		k = depth - i - j;
		for (n = 0; n < 3; n++) {
		    w0[n] = (i*v0[n] + j*v1[n] + k*v2[n])/depth;
		    w1[n] = ((i+1)*v0[n] + j*v1[n] + (k-1)*v2[n])/depth;
		    w2[n] = (i*v0[n] + (j+1)*v1[n] + (k-1)*v2[n])/depth;
		}
		l = Math.sqrt(w0[0]*w0[0] + w0[1]*w0[1] + w0[2]*w0[2]);
		w0[0] /= l; w0[1] /= l; w0[2] /= l;
		l = Math.sqrt(w1[0]*w1[0] + w1[1]*w1[1] + w1[2]*w1[2]);
		w1[0] /= l; w1[1] /= l; w1[2] /= l;
		l = Math.sqrt(w2[0]*w2[0] + w2[1]*w2[1] + w2[2]*w2[2]);
		w2[0] /= l; w2[1] /= l; w2[2] /= l;
		recorditem(w1, w0, w2, p0, radius, shadeType, avnormal);
	    }
	}
	for (i = 0; i < depth-1; i++) {
	    for (j = 0; i + j < depth-1; j++) {
		k = depth - i - j;
		for (n = 0; n < 3; n++) {
		    w0[n] = ((i+1)*v0[n] + (j+1)*v1[n] + (k-2)*v2[n])/depth;
		    w1[n] = ((i+1)*v0[n] + j*v1[n] + (k-1)*v2[n])/depth;
		    w2[n] = (i*v0[n] + (j+1)*v1[n] + (k-1)*v2[n])/depth;
		}
		l = Math.sqrt(w0[0]*w0[0] + w0[1]*w0[1] + w0[2]*w0[2]);
		w0[0] /= l; w0[1] /= l; w0[2] /= l;
		l = Math.sqrt(w1[0]*w1[0] + w1[1]*w1[1] + w1[2]*w1[2]);
		w1[0] /= l; w1[1] /= l; w1[2] /= l;
		l = Math.sqrt(w2[0]*w2[0] + w2[1]*w2[1] + w2[2]*w2[2]);
		w2[0] /= l; w2[1] /= l; w2[2] /= l;
		recorditem(w0, w1, w2, p0, radius, shadeType, avnormal);
	    }
	}
    }
*/

    private void drawBox (float size, int type) {
	float n [][] = {
	    {-1.0f,  0.0f, 0.0f}, {0.0f, 1.0f, 0.0f}, {1.0f, 0.0f,  0.0f},
	    { 0.0f, -1.0f, 0.0f}, {0.0f, 0.0f, 1.0f}, {0.0f, 0.0f, -1.0f}};
	int faces [][] = {
	    {0, 1, 2, 3}, {3, 2, 6, 7}, {7, 6, 5, 4},
	    {4, 5, 1, 0}, {5, 6, 2, 1}, {7, 4, 0, 3}};
	float v[][] = new float [8][3];
	int i;

	v [0][0] = v [1][0] = v [2][0] = v [3][0] = -size / 2;
	v [4][0] = v [5][0] = v [6][0] = v [7][0] =  size / 2;
	v [0][1] = v [1][1] = v [4][1] = v [5][1] = -size / 2;
	v [2][1] = v [3][1] = v [6][1] = v [7][1] =  size / 2;
	v [0][2] = v [3][2] = v [4][2] = v [7][2] = -size / 2;
	v [1][2] = v [2][2] = v [5][2] = v [6][2] =  size / 2;

	for (i = 5; i >= 0; i--) {
	    JavaGL.glBegin (type);
	    	JavaGL.glNormal3fv (n [i]);
		JavaGL.glVertex3fv (v [faces [i][0]]);
		JavaGL.glVertex3fv (v [faces [i][1]]);
		JavaGL.glVertex3fv (v [faces [i][2]]);
		JavaGL.glVertex3fv (v [faces [i][3]]);
	    JavaGL.glEnd ();
	 }
    }

    private void doughnut (float r, float R, int nsides, int rings){
	int i, j;
	float theta, phi, theta1;
	float cosTheta, sinTheta;
	float cosTheta1, sinTheta1;
	float ringDelta, sideDelta;

	ringDelta = 2.0f * (float)Math.PI / rings;
	sideDelta = 2.0f * (float)Math.PI / nsides;

	theta = 0.0f;
	cosTheta = 1.0f;
	sinTheta = 0.0f;

	for (i = rings - 1; i >= 0; i--) {
	    theta1 = theta + ringDelta;
	    cosTheta1 = (float)Math.cos (theta1);
	    sinTheta1 = (float)Math.sin (theta1);
	    JavaGL.glBegin (GL.GL_QUAD_STRIP);
	    phi = 0.0f;
	    for (j = nsides; j >= 0; j--) {
		float cosPhi, sinPhi, dist;

		phi += sideDelta;
		cosPhi = (float)Math.cos (phi);
		sinPhi = (float)Math.sin (phi);
		dist = R + r * cosPhi;

		JavaGL.glNormal3f (cosTheta1 * cosPhi, -sinTheta1 * cosPhi, sinPhi);
		JavaGL.glVertex3f (cosTheta1 * dist, -sinTheta1 * dist, r * sinPhi);
		JavaGL.glNormal3f (cosTheta * cosPhi, -sinTheta * cosPhi, sinPhi);
		JavaGL.glVertex3f (cosTheta * dist, -sinTheta * dist,  r * sinPhi);
	    }
	    JavaGL.glEnd();
	    theta = theta1;
	    cosTheta = cosTheta1;
	    sinTheta = sinTheta1;
	}
    }

   private void icosahedron (int shadeType) {
	int i;

	for (i = 19; i >= 0; i--) {
//	    drawtriangle (i, idata, index, shadeType);
	}
    }

    public GL auxGetGL () {
    	return JavaGL;
    }

    public GLU auxGetGLU () {
    	return JavaGLU;
    }

    /**
     * GLUT initialization sub-API.
     */

    /** void glutInitWindowPosition (int x, int y) */
    public void glutInitWindowPosition (int x, int y) {
    	//WindowX = x;		WindowY = y;
    }

    /** void glutInitWindowSize (int width, int height) */
    public void glutInitWindowSize (int width, int height) {
    	//WindowWidth = width;	WindowHeight = height;
    }

    /** void glutMainLoop () */
    public void glutMainLoop () {
    }

    /**
     * GLUT window sub-API.
     */

    /**
     * int glutCreateWindow (const char *title)
     */
    public void glutCreateWindow (Object o) {
    }

    /** void glutPostRedisplay () */
    public void glutPostRedisplay () {
    }

    public void glutSwapBuffers (Object graphics, Object observer) {
    }

    private void QUAD_OBJ_INIT () {
	if (quadObj != null) return;
	quadObj = JavaGLU.gluNewQuadric();
    }

    /** void glutWireSphere (GLdouble radius, GLint slices, GLint stacks) */
    public void glutWireSphere (double radius, int slices, int stacks) {
	QUAD_OBJ_INIT();
	JavaGLU.gluQuadricDrawStyle (quadObj, GLU.GLU_LINE);
	JavaGLU.gluQuadricNormals (quadObj, GLU.GLU_SMOOTH);
	/* If we ever changed/used the texture or orientation state
	   of quadObj, we'd need to change it to the defaults here
	   with gluQuadricTexture and/or gluQuadricOrientation. */
	JavaGLU.gluSphere (quadObj, radius, slices, stacks);
    }

    /** void glutSolidSphere (GLdouble radius, GLint slices, GLint stacks) */
    public void glutSolidSphere (double radius, int slices, int stacks) {
	QUAD_OBJ_INIT();
	JavaGLU.gluQuadricDrawStyle (quadObj, GLU.GLU_FILL);
	JavaGLU.gluQuadricNormals (quadObj, GLU.GLU_SMOOTH);
	/* If we ever changed/used the texture or orientation state
	   of quadObj, we'd need to change it to the defaults here
	   with gluQuadricTexture and/or gluQuadricOrientation. */
	JavaGLU.gluSphere (quadObj, radius, slices, stacks);
    }

    /** void glutWireCone (GLdouble base, GLdouble height,
			   GLint slices, GLint stacks) */
    public void glutWireCone (double base, double height,
			      int slices, int stacks) {
	QUAD_OBJ_INIT();
	JavaGLU.gluQuadricDrawStyle (quadObj, GLU.GLU_LINE);
	JavaGLU.gluQuadricNormals (quadObj, GLU.GLU_SMOOTH);
	/* If we ever changed/used the texture or orientation state
	   of quadObj, we'd need to change it to the defaults here
	   with gluQuadricTexture and/or gluQuadricOrientation. */
	JavaGLU.gluCylinder(quadObj, base, 0.0, height, slices, stacks);
     }

    /** void glutSolidCone (GLdouble base, GLdouble height,
			    GLint slices, GLint stacks) */
    public void glutSolidCone (double base, double height,
			       int slices, int stacks) {
	QUAD_OBJ_INIT();
	JavaGLU.gluQuadricDrawStyle (quadObj, GLU.GLU_FILL);
	JavaGLU.gluQuadricNormals (quadObj, GLU.GLU_SMOOTH);
	/* If we ever changed/used the texture or orientation state
	   of quadObj, we'd need to change it to the defaults here
	   with gluQuadricTexture and/or gluQuadricOrientation. */
	JavaGLU.gluCylinder(quadObj, base, 0.0, height, slices, stacks);
     }

    /** void glutWireCube (GLdouble size) */
    public void glutWireCube (double size) {
	drawBox ((float)size, GL.GL_LINE_LOOP);
    }

    /** void glutSolidCube (GLdouble size) */
    public void glutSolidCube (double size) {
	drawBox ((float)size, GL.GL_QUADS);
    }

    /** void glutWireTorus (GLdouble innerRadius, GLdouble outerRadius,
			    GLint nsides, GLint rings) */
    public void glutWireTorus (double innerRadius, double outerRadius,
			       int nsides, int rings) {
	JavaGL.glPushAttrib (GL.GL_POLYGON_BIT);
	JavaGL.glPolygonMode (GL.GL_FRONT_AND_BACK, GL.GL_LINE);
	doughnut ((float)innerRadius, (float)outerRadius, nsides, rings);
	JavaGL.glPopAttrib();
    }

    /** void glutSolidTorus (GLdouble innerRadius, GLdouble outerRadius,
			     GLint nsides, GLint rings) */
    public void glutSolidTorus (double innerRadius, double outerRadius,
			        int nsides, int rings) {
	doughnut ((float)innerRadius, (float)outerRadius, nsides, rings);
    }

    /** void glutWireIcosahedron (void) */
    public void glutWireIcosahedron () {
     	icosahedron (GL.GL_LINE_LOOP);
    }

    /** void glutWireIcosahedron (void) */
    public void glutSolidIcosahedron () {
     	icosahedron (GL.GL_TRIANGLES);
    }

    /** void glutWireTeapot (GLdouble scale) */
    public void glutWireTeapot (double scale) {
    	teapot.Teapot (JavaGL, 10, (float)scale, GL.GL_LINE);
    }

    /** void glutSolidTeapot (GLdouble scale) */
    public void glutSolidTeapot (double scale) {
    	teapot.Teapot (JavaGL, 14, (float)scale, GL.GL_FILL);
    }

    /*
     * GLUT menu sub-API and window callback sub-API are removed.
     */

    /**
     * Dummy constructor. Please call new GLUT (yourGL).
     */
    public GLUT () {
    	//System.out.println ("Please call new GLUT (yourGL)");
    }

    public GLUT (GL myGL) {
		JavaGL = myGL;
    	JavaGLU = new GLU (JavaGL);
    }
}
