/*
 * Copyright (C) 2016 Nikita Mikhailov <nikita.s.mikhailov@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.sufficientlysecure.keychain.securitytoken.usb.tpdu;

import org.sufficientlysecure.keychain.securitytoken.usb.UsbTransportException;

public class IBlock extends Block {
    private static final byte BIT_SEQUENCE = 6;
    private static final byte BIT_CHAINING = 5;

    public IBlock(final Block baseBlock) {
        super(baseBlock);
    }

    public IBlock(BlockChecksumType checksumType, byte nad, byte sequence, boolean chaining,
                  byte[] apdu) throws UsbTransportException {
        super(checksumType, nad,
                (byte) (((sequence & 1) << BIT_SEQUENCE) | (chaining ? 1 << BIT_CHAINING : 0)),
                apdu);
    }

    public byte getSequence() {
        return (byte) ((getPcb() >> BIT_SEQUENCE) & 1);
    }

    public boolean getChaining() {
        return ((getPcb() >> BIT_CHAINING) & 1) != 0;
    }
}