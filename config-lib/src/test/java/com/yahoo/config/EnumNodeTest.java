// Copyright 2017 Yahoo Holdings. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root.
package com.yahoo.config;

import edu.umd.cs.findbugs.annotations.NonNull;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author lulf
 * @since 5.1
 */
public class EnumNodeTest {
    private static class MyNode extends EnumNode<MyNode.Enum> {
        public enum Enum { ONE, TWO }
        public final static Enum ONE = Enum.ONE;
        public final static Enum TWO = Enum.TWO;

        @Override
        protected boolean doSetValue(@NonNull String name) {
          try {
            value = Enum.valueOf(name);
            return true;
          } catch (IllegalArgumentException e) {
          }
          return false;
        }

    }

    @Test
    public void testEnumNode() {
        MyNode n = new MyNode();
        assertNull(n.getValue());
        assertThat(n.toString(), is("(null)"));
        assertTrue(n.doSetValue("ONE"));
        assertThat(n.getValue(), is("ONE"));
        assertThat(n.toString(), is("ONE"));
        assertFalse(n.doSetValue("THREE"));
    }
}
