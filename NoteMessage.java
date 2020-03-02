
public class NoteMessage extends Message
{

    /**
     *
     */
    private static final long serialVersionUID = -8032409647178565587L;

	public NoteMessage(Node sender, String note)
	{
		super(sender);
		this.type = NOTE;
		this.msgText = note;
	}

}
