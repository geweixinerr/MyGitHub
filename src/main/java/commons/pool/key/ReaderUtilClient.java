package commons.pool.key;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * This file is one of the source files for the examples contained in
 * /src/site/xdoc/examples.xml
 * It is not intended to be included in a source release.
 */

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.commons.pool2.KeyedObjectPool;
import org.apache.commons.pool2.KeyedPooledObjectFactory;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;

/**
 * Instantiates and uses a ReaderUtil. The GenericObjectPool supplied to the constructor will have
 * default configuration properties.
 */
public class ReaderUtilClient {

	public static void main(String[] args) throws IllegalStateException, UnsupportedOperationException, Exception {
    	
    	//对象池配置
    	GenericKeyedObjectPoolConfig<StringBuffer> conf = new GenericKeyedObjectPoolConfig<StringBuffer>();
    	
    	conf.setMaxTotal(3);
    	conf.setBlockWhenExhausted(false);
    
    	KeyedPooledObjectFactory<String, StringBuffer> poolFactory = new StringBufferFactory();
    	
    	KeyedObjectPool<String, StringBuffer> pool = new GenericKeyedObjectPool<String,StringBuffer>(poolFactory,conf);

        ReaderUtil readerUtil = new ReaderUtil(pool);
        
        System.out.println("激活的数量: " + pool.getNumActive());
      //  pool.borrowObject("mc");
      //  pool.borrowObject("mc");
//        pool.borrowObject("mc");

        pool.addObject("Java");
        pool.borrowObject("Java");
        
        System.out.println("激活的数量: " + pool.getNumActive());
        Reader reader = new StringReader("foo");
        try {
            System.out.println(readerUtil.readToString(reader));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("激活的数量: " + pool.getNumActive());

    }
}
