/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.view.models;

import hr.algebra.model.Director;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * @author antem_jizaagf
 */
public class DirectorTransferable implements Transferable{

    public static final DataFlavor DIRECTOR_FLAVOR_DATA = new DataFlavor(Director.class, "Director");
    private static final DataFlavor[] FLAVORS = {DIRECTOR_FLAVOR_DATA};

    private final Director director;
    
    public DirectorTransferable(Director director) {
        this.director = director;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return FLAVORS;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return DIRECTOR_FLAVOR_DATA.equals(flavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (DIRECTOR_FLAVOR_DATA.equals(flavor)) {
            return director;
        }

        throw new UnsupportedFlavorException(flavor);
    }
}
