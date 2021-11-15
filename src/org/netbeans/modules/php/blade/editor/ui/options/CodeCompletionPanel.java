/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2016 Oracle and/or its affiliates. All rights reserved.
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
 */
package org.netbeans.modules.php.blade.editor.ui.options;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;
import javax.swing.JComponent;
import org.netbeans.modules.options.editor.spi.PreferencesCustomizer;
import org.openide.util.HelpCtx;

/**
 * Based on PHP editor.
 */
@org.netbeans.api.annotations.common.SuppressWarnings({"SE_BAD_FIELD_STORE"})
public class CodeCompletionPanel extends javax.swing.JPanel {
    private static final long serialVersionUID = -3783460333563884705L;

    private final Preferences preferences;
    private final ItemListener defaultCheckBoxListener = new DefaultCheckBoxListener();
    private final Map<String, Object> id2Saved = new HashMap<>();

    public CodeCompletionPanel(Preferences preferences) {
        assert preferences != null;

        this.preferences = preferences;

        initComponents();

        initAutoCompletion();
    }

    public static PreferencesCustomizer.Factory getCustomizerFactory() {
        return (Preferences pref) -> new CodeCompletionPreferencesCustomizer(pref);
    }

    private void initAutoCompletion() {
        boolean codeCompletionEchoDelimiters = preferences.getBoolean(OptionsUtils.AUTO_COMPLETION_ECHO_DELIMITERS,
                OptionsUtils.AUTO_COMPLETION_ECHO_DELIMITER_DEFAULT
        );
        boolean codeCompletionEscapedEchoDelimiters = preferences.getBoolean(OptionsUtils.AUTO_COMPLETION_ESCAPED_ECHO_DELIMITERS,
                OptionsUtils.AUTO_COMPLETION_ESCAPED_ECHO_DELIMITER_DEFAULT
        );
        autoCompletionEchoDelimiterCheckBox.setSelected(codeCompletionEchoDelimiters);
        autoCompletionEchoDelimiterCheckBox.addItemListener(defaultCheckBoxListener);
        autoCompletionEscapedEchoDelimitersCheckBox.setSelected(codeCompletionEscapedEchoDelimiters);
        autoCompletionEscapedEchoDelimitersCheckBox.addItemListener(defaultCheckBoxListener);
        id2Saved.put(OptionsUtils.AUTO_COMPLETION_ECHO_DELIMITERS, autoCompletionEchoDelimiterCheckBox.isSelected());
        id2Saved.put(OptionsUtils.AUTO_COMPLETION_ESCAPED_ECHO_DELIMITERS, autoCompletionEscapedEchoDelimitersCheckBox.isSelected());
    }

    void validateData() {
        preferences.putBoolean(OptionsUtils.AUTO_COMPLETION_ECHO_DELIMITERS, autoCompletionEchoDelimiterCheckBox.isSelected());
        preferences.putBoolean(OptionsUtils.AUTO_COMPLETION_ESCAPED_ECHO_DELIMITERS, autoCompletionEscapedEchoDelimitersCheckBox.isSelected());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        autoCompletionSmartQuotesDelimitersLabel = new javax.swing.JLabel();
        autoCompletionEscapedEchoDelimitersCheckBox = new javax.swing.JCheckBox();
        autoCompletionEchoDelimiterCheckBox = new javax.swing.JCheckBox();

        org.openide.awt.Mnemonics.setLocalizedText(autoCompletionSmartQuotesDelimitersLabel, org.openide.util.NbBundle.getMessage(CodeCompletionPanel.class, "CodeCompletionPanel.autoCompletionSmartQuotesDelimitersLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(autoCompletionEscapedEchoDelimitersCheckBox, org.openide.util.NbBundle.getMessage(CodeCompletionPanel.class, "CodeCompletionPanel.autoCompletionEscapedEchoDelimitersCheckBox.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(autoCompletionEchoDelimiterCheckBox, org.openide.util.NbBundle.getMessage(CodeCompletionPanel.class, "CodeCompletionPanel.autoCompletionEchoDelimiterCheckBox.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(autoCompletionSmartQuotesDelimitersLabel)
                    .addComponent(autoCompletionEscapedEchoDelimitersCheckBox)
                    .addComponent(autoCompletionEchoDelimiterCheckBox))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(autoCompletionSmartQuotesDelimitersLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(autoCompletionEchoDelimiterCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(autoCompletionEscapedEchoDelimitersCheckBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox autoCompletionEchoDelimiterCheckBox;
    private javax.swing.JCheckBox autoCompletionEscapedEchoDelimitersCheckBox;
    private javax.swing.JLabel autoCompletionSmartQuotesDelimitersLabel;
    // End of variables declaration//GEN-END:variables

    private final class DefaultCheckBoxListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            validateData();
        }
    }

    static final class CodeCompletionPreferencesCustomizer implements PreferencesCustomizer {

        private final Preferences preferences;
        private CodeCompletionPanel component;

        private CodeCompletionPreferencesCustomizer(Preferences preferences) {
            this.preferences = preferences;
        }

        @Override
        public String getId() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public String getDisplayName() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public HelpCtx getHelpCtx() {
            return new HelpCtx("org.netbeans.modules.php.blade.editor.ui.options.CodeCompletionPanel");
        }

        @Override
        public JComponent getComponent() {
            if (component == null) {
                component = new CodeCompletionPanel(preferences);
            }
            return component;
        }
    }

    String getSavedValue(String key) {
        return id2Saved.get(key).toString();
    }

    public static final class CustomCustomizerImpl extends PreferencesCustomizer.CustomCustomizer {

        @Override
        public String getSavedValue(PreferencesCustomizer customCustomizer, String key) {
            if (customCustomizer instanceof CodeCompletionPreferencesCustomizer) {
                return ((CodeCompletionPanel) customCustomizer.getComponent()).getSavedValue(key);
            }
            return null;
        }
    }

}
