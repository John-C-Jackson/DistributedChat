
public class LeftMessage extends Message
{

    /**
     *
     */
    private static final long serialVersionUID = -3777366793930312980L;

	public LeftMessage(String sender)
	{
		super(sender);
		this.type = LEAVE;
		this.msgText = typeToMsg(type);
	}

}
