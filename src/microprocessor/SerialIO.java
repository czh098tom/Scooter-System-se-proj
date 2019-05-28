package microprocessor;

import java.io.*;
import java.util.*;
import gnu.io.*; 

public class SerialIO extends IOManager{

	private CommPortIdentifier portId;
	private CommPort com;
	private SerialPort ser;
	private Scanner serScanner;
	private OutputStream serOS;
	
	public SerialIO() {
        try {
			// TODO: identify the COM port from Windows' control panel
            portId = CommPortIdentifier.getPortIdentifier("COM3");

            com = portId.open("MCS51COM", 2000);
            ser = (SerialPort)com;
			// Baud rate = 9600, Data bits = 8, 1 stop bit, Parity OFF
            ser.setSerialPortParams(9600, SerialPort.DATABITS_8, 
                                    SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            serScanner=new Scanner(ser.getInputStream());
            serOS=ser.getOutputStream();
        } catch (Exception e){
            e.printStackTrace(System.out);
        }
	}

	@Override
	public byte getFrom() {
		return serScanner.nextByte();
	}

	@Override
	public void setTo(byte value) {
		try {
			serOS.write(value);
		}
		catch(Exception e){
            e.printStackTrace(System.out);
        }
	}

	public void finalize() {
		ser.close();
	}
}
