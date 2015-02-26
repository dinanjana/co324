package monitor;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Date;

public class Reading {
	/**
	 * The size required to store a reading.
	 */
	static final int SIZE = Long.BYTES * 2 
			+ Integer.BYTES + Float.BYTES;
	/**
	 * ByteBuffer is a handy type for storing binary data.
	 */
	final ByteBuffer buf;
	final int length = SIZE + Integer.BYTES;
	/**
	 * Constructs a Reading from the given values. 
	 */
	public Reading(long id, long time, int pulse, float temp) {
		buf = ByteBuffer.allocate(length);
                buf.putInt(length);
		buf.putLong(id);
		buf.putLong(time);
		buf.putInt(pulse);
		buf.putFloat(temp);		
               
	}
	
	/**
	 * Constructs a Reading from the given stream. 
	 */	
	public Reading(DataInputStream sin) throws IOException {
		byte[] a = new byte[length];
		sin.readFully(a);
		buf = ByteBuffer.wrap(a);
	}
	
	/**
	 * @return the underlying data array.
	 */
	byte[] data() {
		return buf.array();
	}
	
	/**
	 * Reading data rendered as a string. 
	 */
	@Override
	public String toString() {
		return "length:"+buf.getInt()
                        +", ID:" + buf.getLong()
			+ ", time:" + new Date(buf.getLong())
			+ ", pulse:" + buf.getInt()
			+ ", temp:" + buf.getFloat()
                         ;
	}
	
	/**
	 * A quick unit test for the class. 
	 */
	public static void main(String[] args) throws IOException {
		Reading p1 = new Reading(12345l, 
				System.currentTimeMillis(),
				60,	37.5f);
		
		DataInputStream din = new DataInputStream( 
				new ByteArrayInputStream(p1.data()));
		System.out.println(din);
		Reading p2 = new Reading(din);
                System.out.println(p2.toString());
		
		assert Arrays.equals(p1.data(), p2.data());
	}
}
