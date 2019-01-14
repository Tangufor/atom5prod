package atom5;

import java.io.Serializable;

public class MessageType implements Serializable {
	protected static final long serialVersionUID = 1112122200L;
	
		static final int USERID = 0;
		static final int TEXT = 1;
		static final int FILE = 2;
		static final int LOGOUT = 3;
		static final int ISFILE = 4;
		private int type;
		private Object data;
		
		public MessageType(int type, Object data){
			this.type = type;
			this.data = data;
		}
		public int getType(){
			return type;
		}
		public Object getData(){
			return data;
		}
			

}
