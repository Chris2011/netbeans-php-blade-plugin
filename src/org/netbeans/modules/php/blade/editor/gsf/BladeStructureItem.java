/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2016 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 *
 * Contributor(s):
 *
 * Portions Copyrighted 2016 Sun Microsystems, Inc.
 */
package org.netbeans.modules.php.blade.editor.gsf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.swing.ImageIcon;
import org.netbeans.modules.php.blade.editor.parsing.BladeParserResult;
import org.netbeans.lib.editor.util.CharSequenceUtilities;
import org.netbeans.modules.csl.api.ElementHandle;
import org.netbeans.modules.csl.api.ElementKind;
import org.netbeans.modules.csl.api.HtmlFormatter;
import org.netbeans.modules.csl.api.Modifier;
import org.netbeans.modules.csl.api.OffsetRange;
import org.netbeans.modules.csl.api.StructureItem;
import org.netbeans.modules.csl.spi.ParserResult;
import org.netbeans.modules.parsing.api.Snapshot;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Haidu Bogdan
 */
public class BladeStructureItem implements StructureItem {
    List<BladeStructureItem> directives;
    BladeParserResult.Directive item;
    Snapshot snapshot;
    
    public BladeStructureItem( Snapshot snapshot, BladeParserResult.Directive item, List<BladeParserResult.Directive> directives ) {
        
        this.item = item;
        this.directives = new ArrayList<BladeStructureItem>();
        this.snapshot = snapshot;
        
        for ( BladeParserResult.Directive current : directives ) {

            if ( 
                item.getOffset() < current.getOffset() && 
                current.getOffset() + current.getLength() < item.getOffset() + item.getLength() 
            ) {

                this.directives.add( new BladeStructureItem( snapshot, current, directives ) );

            }

        }
        
    }
    
    @Override
    public String getName() {
        return "Directive " + item.getDescription();
    }

    @Override
    public String getSortText() {
        return "Directive " + item.getDescription();
    }

    @Override
    public String getHtml(HtmlFormatter hf) {
        return "Directive " + item.getDescription();
    }

    @Override
    public ElementHandle getElementHandle() {
        return new BladeElementHandle( item, snapshot );
    }

    @Override
    public ElementKind getKind() {
        return ElementKind.ATTRIBUTE;
    }

    @Override
    public Set<Modifier> getModifiers() {
        if ( CharSequenceUtilities.startsWith( item.getDescription(), "*" ) )
            return Collections.singleton( Modifier.STATIC );
        return Collections.emptySet();
    }

    @Override
    public boolean isLeaf() {
        return directives.isEmpty();
    }

    @Override
    public List<? extends StructureItem> getNestedItems() {
        return directives;
    }

    @Override
    public long getPosition() {
        return item.getOffset();
    }

    @Override
    public long getEndPosition() {
        return item.getOffset() + item.getLength();
    }

    @Override
    public ImageIcon getCustomIcon() {
        return null;
    }
    
    class BladeElementHandle implements ElementHandle {

        BladeParserResult.Directive item;
        Snapshot snapshot;
        
        public BladeElementHandle( BladeParserResult.Directive item, Snapshot snapshot ) {
            this.item = item;
            this.snapshot = snapshot;
        }
        
        @Override
        public FileObject getFileObject() {
            return snapshot.getSource().getFileObject();
        }

        @Override
        public String getMimeType() {
            return BladeLanguage.BLADE_MIME_TYPE;
        }

        @Override
        public String getName() {
            return "Directive " + item.getDescription();
        }

        @Override
        public String getIn() {
            return "Directive " + item.getExtra();
        }

        @Override
        public ElementKind getKind() {
            return ElementKind.ATTRIBUTE;
        }

        @Override
        public Set<Modifier> getModifiers() {
            if ( CharSequenceUtilities.startsWith( item.getDescription(), "*" ) )
                return Collections.singleton( Modifier.STATIC );
            return Collections.emptySet();
        }

        @Override
        public boolean signatureEquals(ElementHandle eh) {
            if ( !(eh instanceof BladeElementHandle) ) return false;
            if ( eh.getName().equals(this.getName()) ) return true;
            return false;
        }

        @Override
        public OffsetRange getOffsetRange( ParserResult pr ) {
            return new OffsetRange( item.getOffset(), item.getOffset() + item.getLength() );
        }
        
        
    }
}
