/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.view.models;

import hr.algebra.model.Actor;
import hr.algebra.model.Director;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * @author antem_jizaagf
 */
public class ActorTransferable implements Transferable{

    public static final DataFlavor ACTOR_FLAVOR_DATA = new DataFlavor(Actor.class, "Actor");
    private static final DataFlavor[] FLAVORS = {ACTOR_FLAVOR_DATA};

    private final Actor actor;

    public ActorTransferable(Actor actor) {
        this.actor = actor;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return FLAVORS;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return ACTOR_FLAVOR_DATA.equals(flavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (ACTOR_FLAVOR_DATA.equals(flavor)) {
            return actor;
        }

        throw new UnsupportedFlavorException(flavor);
    }
}
