
package fr.esiea.fc.tasks.document.reports.generic;

import fr.esiea.fc.control.Report;
import fr.esiea.fc.control.SessionManager;
import fr.esiea.fc.model.security.TaskDAO;
import fr.esiea.fc.util.Log;
import fr.esiea.fc.util.error.ControllerActionUnknown;
import fr.esiea.fc.util.error.DataMissingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * Controller for document's report
 * @author Michel Messak
 */
public class DocumentReportController implements Controller {


    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] fields = new String[]{"Prénom","Nom","Date de Naissance","Email","Actif"};
        String[] widths = {"20%", "20%", "15%", "30%", "10%","5%"};
        String[] aligns = {"center", "center", "center","center", "Center", "center"};
        Boolean[] visible = {true, true, true,true, true, true};
        Boolean[] sort = {true, true, true, true,true,  false};
        Boolean[] search = {true, true, true,true, true, false};

        String[] colExtras = {
            "<img src=\"images/status_{7}.png\"/>",
            "<form action=\"docFiles.do\" method=\"post\"><input type=\"hidden\" name=\"emp_id\" value=\"{0}\"/><input type=\"hidden\" name=\"dty_id\" value=\"{1}\"/><input type=\"hidden\" name=\"dst_id\" value=\"{2}\"/><input type=\"hidden\" name=\"doc_id\" value=\"{3}\"/><input type=\"image\" src=\"images/files_open_{7}.png\" style=\"width: 20px; height: 20px;\" title=\"Document\"/></form>",
             };

        try {

            Report report = null;

            if (!SessionManager.hasSession(request)) {
                return SessionManager.getLoginView(request);
            }
            request.getAttributeNames();

            if (Report.isFilterCall(request)) {
                
                report = new Report(fields, request.getRequestURL().toString());
                report.setColumnAlignments(aligns);
                report.setColumnWidths(widths);
                report.setColumnExtras(colExtras);
                report.setColumnVisibles(visible);
                report.setColumnSortables(sort);
                report.setColumnSearchables(search);

                 request.getSession().setAttribute("report", report);

                report.ExecuteQuery(request);

                ModelAndView mv = new ModelAndView("report");
                mv.addObject("data", report);

                mv.addObject("title", TaskDAO.selectNameTask(request.getRequestURI().substring(request.getContextPath().length()+1)));
                
                return mv;
            }

            else if (Report.isAjaxCall(request))
            {
                try
                {
                    report = (Report) request.getSession().getAttribute("report");
                    if (report == null) {
                        throw new DataMissingException("Les données n'ont pas pu être récupéré");
                    }

                    report.configureDatatableParametersDocuments(request);
                    ModelAndView mv = new ModelAndView("pager");
                    mv.addObject("data", report);
                    return mv;
                }
                catch(Exception ex)
                {
                    ModelAndView mv = new ModelAndView("pager");
                    return mv;
                }

            }
            else {
                throw new ControllerActionUnknown("Appel à la consultation des documents inconnu");
            }
        } catch (Exception ex) {
            ModelAndView mv = new ModelAndView("error");
            mv.addObject("exception", ex);
            Log.write(ex);
            return mv;
        }
    }

}
