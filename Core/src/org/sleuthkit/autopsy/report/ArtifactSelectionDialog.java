/*
 * Autopsy Forensic Browser
 *
 * Copyright 2012 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.report;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import org.sleuthkit.autopsy.coreutils.Logger;
import org.sleuthkit.datamodel.BlackboardArtifact;
import org.sleuthkit.datamodel.BlackboardArtifact.ARTIFACT_TYPE;

public class ArtifactSelectionDialog extends javax.swing.JDialog {
    private static final Logger logger = Logger.getLogger(ArtifactSelectionDialog.class.getName());
    private static ArtifactSelectionDialog instance;
    private ArtifactModel model;
    private ArtifactRenderer renderer;
    
    private Map<BlackboardArtifact.ARTIFACT_TYPE, Boolean> artifactStates;
    private List<BlackboardArtifact.ARTIFACT_TYPE> artifacts;
    
    /**
     * Creates new form ArtifactSelectionDialog
     */
    public ArtifactSelectionDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        populateList();
        customInit();
    }
    
    /**
     * Populate the list of artifacts with all important artifacts.
     */
    private void populateList() {
        artifacts = getImportantArtifactTypes();
        artifactStates = new EnumMap<BlackboardArtifact.ARTIFACT_TYPE, Boolean>(BlackboardArtifact.ARTIFACT_TYPE.class);
        for (BlackboardArtifact.ARTIFACT_TYPE type : artifacts) {
            artifactStates.put(type, Boolean.TRUE);
        }
    }
    
    private void customInit() {
        model = new ArtifactModel();
        renderer = new ArtifactRenderer();
        artifactList.setModel(model);
        artifactList.setCellRenderer(renderer);
        artifactList.setVisibleRowCount(-1);
        
        artifactList.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                int index = list.locationToIndex(evt.getPoint());
                BlackboardArtifact.ARTIFACT_TYPE type = (BlackboardArtifact.ARTIFACT_TYPE) model.getElementAt(index);
                artifactStates.put(type, !artifactStates.get(type));
                list.repaint();
            }
        });
    }
    
    /**
     * Returns a list of the artifact types we want to report on.
     */
    static List<ARTIFACT_TYPE> getImportantArtifactTypes() {
        List<ARTIFACT_TYPE> types = new ArrayList<ARTIFACT_TYPE>();
        types.add(ARTIFACT_TYPE.TSK_WEB_BOOKMARK);
        types.add(ARTIFACT_TYPE.TSK_WEB_COOKIE);
        types.add(ARTIFACT_TYPE.TSK_WEB_HISTORY);
        types.add(ARTIFACT_TYPE.TSK_WEB_DOWNLOAD);
        types.add(ARTIFACT_TYPE.TSK_RECENT_OBJECT);
        types.add(ARTIFACT_TYPE.TSK_INSTALLED_PROG);
        types.add(ARTIFACT_TYPE.TSK_KEYWORD_HIT);
        types.add(ARTIFACT_TYPE.TSK_HASHSET_HIT);
        types.add(ARTIFACT_TYPE.TSK_DEVICE_ATTACHED);
        types.add(ARTIFACT_TYPE.TSK_WEB_SEARCH_QUERY);
        types.add(ARTIFACT_TYPE.TSK_METADATA_EXIF);
        types.add(ARTIFACT_TYPE.TSK_TAG_FILE);
        types.add(ARTIFACT_TYPE.TSK_TAG_ARTIFACT);
        return types;
    }
    
    /**
     * Display this dialog, and return the selected artifacts.
     */
    Map<BlackboardArtifact.ARTIFACT_TYPE, Boolean> display() {
        this.setTitle("Advanced Artifact Selection");
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        // set the popUp window / JFrame
        int w = this.getSize().width;
        int h = this.getSize().height;

        // set the location of the popUp Window on the center of the screen
        setLocation((screenDimension.width - w) / 2, (screenDimension.height - h) / 2);
        
        this.setVisible(true);
        return artifactStates;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        artifactScrollPane = new javax.swing.JScrollPane();
        artifactList = new javax.swing.JList();
        okButton = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        selectAllButton = new javax.swing.JButton();
        deselectAllButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        artifactList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        artifactList.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        artifactScrollPane.setViewportView(artifactList);

        org.openide.awt.Mnemonics.setLocalizedText(okButton, org.openide.util.NbBundle.getMessage(ArtifactSelectionDialog.class, "ArtifactSelectionDialog.okButton.text")); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(titleLabel, org.openide.util.NbBundle.getMessage(ArtifactSelectionDialog.class, "ArtifactSelectionDialog.titleLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(selectAllButton, org.openide.util.NbBundle.getMessage(ArtifactSelectionDialog.class, "ArtifactSelectionDialog.selectAllButton.text")); // NOI18N
        selectAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAllButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(deselectAllButton, org.openide.util.NbBundle.getMessage(ArtifactSelectionDialog.class, "ArtifactSelectionDialog.deselectAllButton.text")); // NOI18N
        deselectAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deselectAllButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(artifactScrollPane)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(deselectAllButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(selectAllButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleLabel)
                        .addGap(0, 221, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(okButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(artifactScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(selectAllButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deselectAllButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(11, 11, 11)
                .addComponent(okButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    private void selectAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAllButtonActionPerformed
        for (ARTIFACT_TYPE type : artifacts) {
            artifactStates.put(type, Boolean.TRUE);
        }
        artifactList.repaint();
    }//GEN-LAST:event_selectAllButtonActionPerformed

    private void deselectAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deselectAllButtonActionPerformed
        for (ARTIFACT_TYPE type : artifacts) {
            artifactStates.put(type, Boolean.FALSE);
        }
        artifactList.repaint();
    }//GEN-LAST:event_deselectAllButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList artifactList;
    private javax.swing.JScrollPane artifactScrollPane;
    private javax.swing.JButton deselectAllButton;
    private javax.swing.JButton okButton;
    private javax.swing.JButton selectAllButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables

    private class ArtifactModel implements ListModel {

        @Override
        public int getSize() {
            return artifacts.size();
        }

        @Override
        public Object getElementAt(int index) {
            return artifacts.get(index);
        }

        @Override
        public void addListDataListener(ListDataListener l) {
        }

        @Override
        public void removeListDataListener(ListDataListener l) {
        }
    }
    
    private class ArtifactRenderer extends JCheckBox implements ListCellRenderer<BlackboardArtifact.ARTIFACT_TYPE> {

        @Override
        public Component getListCellRendererComponent(JList list, BlackboardArtifact.ARTIFACT_TYPE  value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value != null) {
                setEnabled(list.isEnabled());
                setSelected(artifactStates.get(value));
                setFont(list.getFont());
                setBackground(list.getBackground());
                setForeground(list.getForeground());
                setText(value.getDisplayName());
                return this;
            }
            return new JLabel();
        }
        
    }

}
