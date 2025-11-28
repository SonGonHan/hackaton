package com.hackaton.task.trip.application.usecase;

import com.hackaton.task.trip.adapter.in.web.dto.TripRoadResponse;
import com.hackaton.task.trip.application.in.MakeRoadUseCase;
import com.hackaton.task.trip.application.in.command.MakeRoadCommand;
import com.hackaton.task.trip.application.out.AttractionRepository;
import com.hackaton.task.trip.domain.Attraction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MakeRoadService implements MakeRoadUseCase {

    private final AttractionRepository attractionRepository;

    @Override
    public TripRoadResponse makeRoad(MakeRoadCommand makeRoadCommand) {
        double[][] distances = makeDistMatrix(makeRoadCommand.attractions());
        List<Long> ids = findMaxRoute(makeRoadCommand.attractions().size(), makeRoadCommand.distance(), distances);
        List<Attraction> ans = attractionRepository.findAllById(ids);

        return new TripRoadResponse(ans);
    }

    private static List<Long> findMaxRoute(int n, int maxDistance, double[][] dist) {
        double[][] dp = new double[1 << n][n];
        List<Long>[][] paths = new ArrayList[1 << n][n];

        for (int i = 0; i < (1 << n); i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE/3);
        }

        for (int i = 0; i < n; i++) {
            dp[1 << i][i] = 0;
            paths[1 << i][i] = new ArrayList<>();
            paths[1 << i][i].add((long) i);
        }

        int bestMask = 0;
        int bestLastNode = 0;
        double bestDistance = 0;

        for (int mask = 1; mask < (1 << n); mask++) {
            int bitsCount = Integer.bitCount(mask);

            for (int last = 0; last < n; last++) {
                double dp_mask_last = dp[mask][last];
                if ((mask & (1 << last)) == 0 || dp_mask_last == Integer.MAX_VALUE/3) {
                    continue;
                }

                if (dp_mask_last <= maxDistance) {
                    if (bitsCount > Integer.bitCount(bestMask) ||
                            (bitsCount == Integer.bitCount(bestMask) && dp_mask_last < bestDistance)
                    ) {
                        bestMask = mask;
                        bestLastNode = last;
                        bestDistance = dp_mask_last;
                    }
                }

                int buf = 1;
                for (int next = 0; next < n; next++) {
                    if ((mask & buf) != 0)  {
                        buf <<= 1;
                        continue;
                    }

                    int newMask = mask | buf;
                    double newDistance = dp_mask_last + dist[last][next];

                    if (newDistance < dp[newMask][next]) {
                        dp[newMask][next] = newDistance;
                        paths[newMask][next] = new ArrayList<>(paths[mask][last]);
                        paths[newMask][next].add((long) next);
                    }
                    buf <<= 1;
                }
            }
        }

        return paths[bestMask][bestLastNode];
    }

    private static double[][] makeDistMatrix(List<Attraction> attractions) {
        int n = attractions.size();
        double[][] dist = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = calculateDistance(attractions.get(i).getLatitude(),
                            attractions.get(i).getLongitude(),
                            attractions.get(j).getLatitude(),
                            attractions.get(j).getLongitude());
                    dist[j][i] = dist[i][j];
                }
            }
        }

        return dist;
    }

    private static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double R = 6378.137;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                    Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                            Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c * 1000; // meters
    }
}
