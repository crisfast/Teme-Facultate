using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Adauga : System.Web.UI.Page
{
    SqlConnection con = new SqlConnection(System.Configuration.ConfigurationManager.ConnectionStrings["BlogDB"].ConnectionString);

    protected void Page_Load(object sender, EventArgs e)
    {
        var roluri = Roles.GetRolesForUser();
        if(roluri.Contains("Standard") || !User.Identity.IsAuthenticated)
        {
          Response.Redirect("Default.aspx");
        }
    }

    protected void butonTrimite_Click(object sender, EventArgs e)
    {
        try
        {
            if (con.State == ConnectionState.Closed)
            {
                con.Open();
            }

            SqlCommand cmd = new SqlCommand("insert into BlogPost(Titlu,Continut,Autor, DataPost,NumePoza,Categorie) values(@titlu,@continut,@autor,@data,@poza,@categ)", con);
            cmd.Parameters.AddWithValue("@titlu", titluTB.Text);
            cmd.Parameters.AddWithValue("@continut", mesajTB.Text);
            cmd.Parameters.AddWithValue("@autor", Page.User.Identity.Name);
            string numePoza = DateTime.Now.Ticks.ToString();
            cmd.Parameters.AddWithValue("@data", DateTime.Now);
            cmd.Parameters.AddWithValue("@poza", numePoza);
            cmd.Parameters.AddWithValue("@categ", categSelect.Text);

            if (FileUpload1.HasFile)
            {
                if (FileUpload1.PostedFile.ContentType.ToLower().EndsWith("jpeg"))
                {
                    FileUpload1.SaveAs(Server.MapPath("~") + "/Images/" + numePoza + ".jpg");
                }
            }
            cmd.ExecuteNonQuery();
            cmd.Dispose();
            Response.Redirect("Default.aspx");
        }
        catch (Exception k)
        {
            Response.Write(k.Message);
            //throw;
        }
        finally { con.Close(); }
    }
}