
public class NoteMessage extends Message
{

    /**
     *
     */
    private static final long serialVersionUID = -8032409647178565587L;

	public NoteMessage(String sender, String note)
	{
		super(sender);
		this.type = NOTE;
		this.msgText = note;
	}

}
