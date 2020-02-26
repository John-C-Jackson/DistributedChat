
public class JoinMessage extends Message
{

    /**
     *
     */
    private static final long serialVersionUID = 8226252348454691425L;

	public JoinMessage(String sender)
	{
		super(sender);
		this.type = JOIN;
		this.msgText = typeToMsg(type);
	}

}
