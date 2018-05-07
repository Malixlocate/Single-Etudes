"""
1. Reade a line of input and determine whether or not it is a valid whether or not it is a valid date
    between the years 1753 and 3000

2.
    Either state the input is invalid and why:
        03 JUN 3004 - INVALID: Year out of range.

    or

    output a valid date in the following format:
    dd<space><first three chars of month name><space>yyyy
    e.g 02 Apr 1996

-----------------------------------------------------------

Valid input order: day month year

day: dd or d or 0d
month: mm or m or 0m
year: yy or yyyy
sperator: - or / or <space>
-----------------------------------------------------------

Example dates:

4-6-92
04/06/1992
3 AUG 97
12-Sep-1955
-----------------------------------------------------------

Notes:
    1. 29th of Feb is only considered a valid ate in leap years

    2. If the year is written with only two digits, the date lies between 1950 and 2049, so 65 means
        1965, and 42 mean 2042

"""
"""from datetime import *; from dateutil.relativedelta import *; from dateutil.parser import parse
import calendar

def date_validation():
    try:
        datetime.date(input("Please enter a date:"))
    except ValueError:
       raise ValueError("etc")

date_validation()
"""
from datetime import datetime
import time

def date_validator(input_date):
  for format in ('%d-%m-%Y')

date = input('Date (mm/dd/yyyy): ')

if(date)

try:
  valid_date = time.strptime(date, '%m/%d/%Y')
  print("that was valid as bro")
except ValueError:
  print('Invalid date!')
















"""
Gregorian Calendar
The Gregorian calendar is the calendar in current use in the Western world, both as the
civil and Christian ecclesiastical calendar.  Instituted by Pope Gregory XIII in 1582, the
calendar has 365 days with an extra day every four years (the leap year) except in years
divisible by 100 but not divisible by 400. Thus, the calendar year has an average length
of 365.2425 days.  The Gregorian calendar replaced the Julian calendar, which had be-
come 10 days out of synchrony with the solar cycle.  In October,  1582,  10 days were
dropped from the calendar.  England and the American colonies were late in adopting
the calendar. In 1752, they dropped 11 days.
"""
