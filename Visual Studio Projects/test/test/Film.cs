using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace test
{
    public class Film
    {
        public string FName { get; set; }
        public int FRating { get; set; }

        public Film(string Name, int Rating) 
        {
            FName = Name;
            FRating = Rating;
        }
    }
}
