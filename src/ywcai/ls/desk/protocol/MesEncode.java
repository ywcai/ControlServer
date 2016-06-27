package ywcai.ls.desk.protocol;

import java.nio.charset.Charset;


import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

public class MesEncode implements MessageEncoder<MesReqInf>{

	public Charset charset;
	public MesEncode(Charset charset){
		this.charset=charset;
	}
	@Override
	public void encode(IoSession ioSession, MesReqInf msg, ProtocolEncoderOutput out) throws Exception {
		// TODO Auto-generated method stub
		IoBuffer buf=IoBuffer.allocate(512).setAutoExpand(true);
		if(msg instanceof ProtocolReq){
			ProtocolReq req=(ProtocolReq) msg;
			buf.put(req.getTag());
			buf.putInt(req.getNameLenth());
			buf.putInt(req.getDataLenth());
			buf.putString(req.getUserName(),charset.newEncoder());
			buf.putString(req.getData(),charset.newEncoder());
		}else
		{
			System.out.println("��������Э�鲻��");
		}
		out.write(buf);
		buf.flip();
	}

}