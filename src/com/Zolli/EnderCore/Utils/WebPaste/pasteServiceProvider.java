package com.Zolli.EnderCore.Utils.WebPaste;

import com.Zolli.EnderCore.Utils.WebPaste.pasteServiceType.PasteServiceType;

public class pasteServiceProvider {
	
	public pasteServiceProvider() {}
	
	public static pasteService getService(PasteServiceType type, boolean isPrivate) {
        switch(type) {
            case PASTEBIN:
                return new pasteBinPasteService(isPrivate);
            case PASTIE:
                return new pastiePasteService(isPrivate);
            default:
                return null;
        }
    }
	
}
