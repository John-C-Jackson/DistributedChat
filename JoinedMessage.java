
public class JoinedMessage extends Message
{

    /**
     *
     */
    private static final long serialVersionUID = -9115597487811740477L;

	public JoinedMessage(Node sender)
	{
		super(sender);
		this.type = JOINED;
		this.msgText = typeToMsg(type);
	}

}
