package microprocessor;

import java.io.*;
import java.util.*;
import gnu.io.*; 

public class SerialIO extends IOManager{

	private CommPortIdentifier portId;
	private CommPort com;
	private SerialPort ser;
	private Scanner serScanner;
	private InputStream serIS;
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
            serIS=ser.getInputStream();
            serScanner=new Scanner(serIS);
            serOS=ser.getOutputStream();
        } catch (Exception e){
            e.printStackTrace(System.out);
        }
	}

	@Override
	public byte getFrom() {
		try {
            while (serIS.available() == 0);
            return (byte)serIS.read();
		}
		catch(Exception e){
            e.printStackTrace(System.out);
            return 0;
        }
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

	@Override
	public void close() {
		ser.close();
	}
}
