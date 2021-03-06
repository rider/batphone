/**
 * Copyright (C) 2011 The Serval Project
 *
 * This file is part of Serval Software (http://www.servalproject.org)
 *
 * Serval Software is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This source code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this source code; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package org.servalproject.dna;

import java.nio.ByteBuffer;

public class SubscriberId {
	private byte []sid;

	public SubscriberId(){
		sid=new byte[32];
		Packet.rand.nextBytes(sid);
	}

	public SubscriberId(String sid){
		this.sid=new byte[32];
		if (sid.length()!=64)
			throw new IllegalArgumentException("Subscriber id's must be 64 characters in length");
		int j=0;
		for (int i=0;i<this.sid.length;i++){
			this.sid[i]=(byte)(
				(Character.digit(sid.charAt(j++),16)<<4) |
				Character.digit(sid.charAt(j++),16)
			);
		}
	}

	public SubscriberId(ByteBuffer b){
		sid=new byte[32];
		b.get(sid);
	}

	public SubscriberId(byte[] sid){
		if (sid.length!=32) throw new IllegalArgumentException("Subscriber id's must be 32 bytes long");
		this.sid=sid;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof SubscriberId) {
			SubscriberId s = (SubscriberId) o;
			for (int i = 0; i < 32; i++)
				if (sid[i] != s.sid[i])
					return false;
			return true;
		}
		return false;
	}

	byte[] getSid(){
		return sid;
	}

	@Override
	public String toString(){
		return Packet.binToHex(sid);
	}
}
