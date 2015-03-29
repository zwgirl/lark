/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2011-2015 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

/**
 * Provides an object model API to process <a href="http://json.org/">JSON</a>.
 *
 * <p>The object model API is a high-level API that provides immutable object
 * models for JSON object and array structures. These JSON structures are
 * represented as object models using the Java types {@link javax.json.JsonObject}
 * and {@link javax.json.JsonArray}. The interface {@code javax.json.JsonObject} provides
 * a {@link java.util.Map} view to access the unordered collection of zero or
 * more name/value pairs from the model. Similarly, the interface
 * {@code JsonArray} provides a {@link java.util.List} view to access the
 * ordered sequence of zero or more values from the model.
 *
 * <p>The object model API uses builder patterns to create and modify
 * these object models. The classes {@link javax.json.JsonObjectBuilder} and 
 * {@link javax.json.JsonArrayBuilder} provide methods to create and modify models
 * of type {@code JsonObject} and {@code JsonArray} respectively.
 *
 * <p>These object models can also be created from an input source using
 * the class {@link javax.json.JsonReader}. Similarly, these object models
 * can be written to an output source using the class {@link javax.json.JsonWriter}.
 *
 * @since JSON Processing 1.0
 * @author Jitendra Kotamraju
 */
package javax.json;