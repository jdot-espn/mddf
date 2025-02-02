/**
 * Copyright (c) 2019 MovieLabs

 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.movielabs.mddflib.tests.junit.util.xml;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.movielabs.mddflib.util.xml.ReusableInputStream;

/**
 * @author L. Levin, Critical Architectures LLC
 *
 */
class ReusableStreamTest {
	private static String rsrcPath = "./test/resources/";
	private static File rsrcDir = new File(rsrcPath);

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() throws IOException {		
		File testFile = new File(rsrcDir, "common/CM_base.xml");
		assertTrue(testFile.canRead());
		InputStream mainStream = new FileInputStream(testFile);
		ReusableInputStream streamSrc = new ReusableInputStream(mainStream);
		int rCnt1 = readStream(streamSrc);
		streamSrc.close();
		int rCnt2 = readStream(streamSrc);
		assertEquals(rCnt1, rCnt2);
		assertEquals(7696, rCnt2);
	}
	
	private int readStream(ReusableInputStream streamSrc ) {
		int rCnt = 0;
		int data = 0;
		while(data > -1) {
			try {
				data = streamSrc.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return rCnt;
			}
			char dc = (char) data;
//			System.out.print(dc);
			rCnt++;
		}
		System.out.println("\n\nCharcter count = "+rCnt);
		return rCnt;		
	}

	
}
