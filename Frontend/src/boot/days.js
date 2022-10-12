import dayjs from "dayjs";
import utc from "dayjs/plugin/utc";
import timezone from "dayjs/plugin/timezone";

dayjs.extend(utc);
dayjs.extend(timezone);

dayjs.getDateUnix = (value = dayjs().unix(), timeZone = "Asia/Tokyo") =>
  dayjs.unix(value).tz(timeZone);
dayjs.getDate = (value = dayjs(), timeZone = "Asia/Tokyo") =>
  dayjs(value).tz(timeZone);

export default dayjs;
