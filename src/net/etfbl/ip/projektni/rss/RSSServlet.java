package net.etfbl.ip.projektni.rss;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.etfbl.ip.projektni.dao.ObjavaDAO;
import net.etfbl.ip.projektni.dto.Dogadjaj;
/**
 * Servlet implementation class RSSServlet
 */
@WebServlet("/rss")
public class RSSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RSSServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        String copyright = "Copyright hold by IP2018";
        String title = "Dogadjaji";
        String description = "RSS dogadjaji";
        String language = "sr";
        String link = "https://www.etf.unibl.org";
        Calendar cal = new GregorianCalendar();
        Date creationDate = cal.getTime();
        SimpleDateFormat date_format = new SimpleDateFormat(
                "EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'Z", Locale.US);
        String pubdate = date_format.format(creationDate);
        Feed rssFeeder = new Feed(title, link, description, language,
                copyright, pubdate);
        ArrayList<Dogadjaj> dogadjaji=new ArrayList<>();
        dogadjaji=ObjavaDAO.selectAllDogadjaj();
        for(Dogadjaj dogadaj:dogadjaji) {
        FeedMessage feed = new FeedMessage();
        feed.setTitle(dogadaj.getNaziv());
        feed.setDescription(dogadaj.getOpis());
        feed.setCategory(dogadaj.getKategorija());
        feed.setStartDate(date_format.format(dogadaj.getDatumOdrzavanja()));
        feed.setPubDate(date_format.format(dogadaj.getVrijemeKreiranja()));
        feed.setGuid(dogadaj.getSlika());
        feed.setLink(dogadaj.getSlika());
        rssFeeder.addFeedMessage(feed);
        }

        RSSFeedWriter writer = new RSSFeedWriter(rssFeeder);
		
		try {
			response.getWriter().println(writer.write());
		} catch (Exception ex) {
			System.out.println(ex.toString());
			response.sendError(500);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
