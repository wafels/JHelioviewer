package org.helioviewer.jhv.gui.components.statusplugins;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;

import org.helioviewer.jhv.gui.IconBank;
import org.helioviewer.jhv.gui.IconBank.JHVIcon;
import org.helioviewer.jhv.layers.LayersModel;
import org.helioviewer.jhv.viewmodel.view.MetaDataView;
import org.helioviewer.jhv.viewmodel.view.View;

/**
 * Status panel for displaying whether the image contains helioviewer meta data
 * or not.
 * 
 * <p>
 * The information of this panel is always shown for the active layer.
 * 
 * <p>
 * If there is no layer present, this panel will be invisible.
 * 
 * @author Markus Langenberg
 */
public class MetaDataStatusPanel extends ViewStatusPanelPlugin {

    private static final long serialVersionUID = 1L;

    private static final Icon ICON_CHECK = IconBank.getIcon(JHVIcon.CHECK);
    private static final Icon ICON_EX = IconBank.getIcon(JHVIcon.EX);

    /**
     * Default constructor.
     */
    public MetaDataStatusPanel() {
        setBorder(BorderFactory.createEtchedBorder());

        setText("Meta:");
        setIcon(ICON_EX);
        setVerticalTextPosition(JLabel.CENTER);
        setHorizontalTextPosition(JLabel.LEFT);
        validate();
        setPreferredSize(null);

        LayersModel.getSingletonInstance().addLayersListener(this);

    }

    /**
     * {@inheritDoc}
     */
    public void activeLayerChanged(int idx) {

        if (LayersModel.getSingletonInstance().isValidIndex(idx)) {
            View view = LayersModel.getSingletonInstance().getLayer(idx);
            if (view != null) {
                setVisible(true);

                MetaDataView metaDataView = view.getAdapter(MetaDataView.class);

                // this operates on the raw viewchain...
                // it might be a good idea to push that functionality to
                // LayersModel
                if (metaDataView != null) {
                    setIcon(ICON_CHECK);
                    
                }

                validate();
                setPreferredSize(null);
            }
        } else {
            setVisible(false);
        }
    }

}
